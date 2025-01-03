# Game_Algorithms
В проекте библиотеки игры heroes_task_lib определено четыре интерфейса, методы которые необходимо было имплементировать в проект heroes_task согласно документации. Это интерфейсы GeneratePreset, SimulateBattle, SuitableForAttackUnitsFinder и UnitTargetPathFinder.

Интерфейсы отвечают за реализацию следующего логического функционала внутри основного проекта игры:

GeneratePreset содержит метод Army generate(List unitList, int maxPoints), который отвечает за генерацию пресета армии противника.

SimulateBattle содержит метод void simulate(Army playerArmy, Army computerArmy) throws InterruptedException; и отвечает за осуществление симуляции боя.

SuitableForAttackUnitsFinder содержит метод List getSuitableUnits(List> unitsByRow, boolean isLeftArmyTarget); и отвечает за создание перечня подходящих для атаки юнитов.

UnitTargetPathFinder содержит метод List getTargetPath(Unit attackUnit, Unit targetUnit, List existingUnitList); и отвечает за поиск кратчайшего пути между атакующим и атакуемым юнитом.

Указанные методы реализованы в полном объеме. Алгоритмическая сложность доказана и соответствует условиям поставленной задачи.
