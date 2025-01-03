package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.SuitableForAttackUnitsFinder;

import java.util.List;

public class SuitableForAttackUnitsFinderImpl implements SuitableForAttackUnitsFinder {

    @Override
    public List<Unit> getSuitableUnits(List<List<Unit>> unitsByRow, boolean isLeftArmyTarget) {
        List<Unit> suitableUnits = new ArrayList<>();
        for (List<Unit> row : unitsByRow) {
            for (int i = row.size() - 1; i >= 0; i--) {
                Unit unit = row.get(i);
                if ((isLeftArmyTarget && unit.getX() < 24) || (!isLeftArmyTarget && unit.getX() > 2)) {
                    suitableUnits.add(unit);
                    break;
                }
            }
        }
// Алгоритмическая сложность: O(n), где n — количество юнитов в ряд.
        return suitableUnits;
    }
}
