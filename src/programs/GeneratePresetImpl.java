package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.GeneratePreset;

import java.util.*;

public class GeneratePresetImpl implements GeneratePreset {

    @Override
    public Army generate(List<Unit> unitList, int maxPoints) {
        List<Unit> selectedUnits = new ArrayList<>();
        int remainingPoints = maxPoints;

        // Сортируем юнитов по эффективности (атака + здоровье) / стоимость в порядке убывания
        unitList.sort((u1, u2) -> Double.compare(
                (u2.getBaseAttack() + u2.getHealth()) / (double) u2.getCost(),
                (u1.getBaseAttack() + u1.getHealth()) / (double) u1.getCost()
        ));

        for (Unit unit : unitList) {
            int maxCount = Math.min(11, remainingPoints / unit.getCost());
            for (int i = 0; i < maxCount; i++) {
                selectedUnits.add(new Unit(unit));
                remainingPoints -= unit.getCost();
                if (remainingPoints < unit.getCost()) break;
            }
        }
// Алгоритмическая сложность: O(n * m), где
// n — количество типов юнитов, m — максимальное число юнитов в армии.

        return new Army(selectedUnits, maxPoints - remainingPoints);
    }
}