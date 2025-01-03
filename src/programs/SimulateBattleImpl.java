package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.programs.PrintBattleLog;
import com.battle.heroes.army.programs.SimulateBattle;

public class SimulateBattleImpl implements SimulateBattle {
    private PrintBattleLog printBattleLog; // Позволяет логировать. Использовать после каждой атаки юнита

    @Override
    public void simulate(Army playerArmy, Army computerArmy) throws InterruptedException {
        PrintBattleLog printBattleLog = new PrintBattleLog();
        List<Unit> playerUnits = new ArrayList<>(playerArmy.getUnits());
        List<Unit> computerUnits = new ArrayList<>(computerArmy.getUnits());

        while (!playerUnits.isEmpty() && !computerUnits.isEmpty()) {
            playerUnits.sort(Comparator.comparingInt(Unit::getBaseAttack).reversed());
            computerUnits.sort(Comparator.comparingInt(Unit::getBaseAttack).reversed());

            List<Unit> allUnits = new ArrayList<>();
            allUnits.addAll(playerUnits);
            allUnits.addAll(computerUnits);
            allUnits.sort(Comparator.comparingInt(Unit::getBaseAttack).reversed());

            for (Unit unit : allUnits) {
                if (!unit.isAlive()) continue;
                Unit target = unit.getProgram().attack();
                if (target != null) {
                    printBattleLog.printBattleLog(unit, target);
                }
            }

            playerUnits.removeIf(u -> !u.isAlive());
            computerUnits.removeIf(u -> !u.isAlive());
// Алгоритмическая сложность: O(n^2 log n), где n — общее количество юнитов.
        }
    }
}