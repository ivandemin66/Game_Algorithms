package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.Edge;
import com.battle.heroes.army.programs.UnitTargetPathFinder;

import java.util.List;

public class UnitTargetPathFinderImpl implements UnitTargetPathFinder {
    @Override
    public List<Edge> getTargetPath(Unit attackUnit, Unit targetUnit, List<Unit> existingUnitList) {
        int[][] grid = new int[27][21];
        for (Unit unit : existingUnitList) {
            grid[unit.getX()][unit.getY()] = -1; // Занятые клетки
        }

        PriorityQueue<EdgeDistance> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.getDistance()));
        queue.add(new EdgeDistance(attackUnit.getX(), attackUnit.getY(), 0));
        Map<String, Edge> visited = new HashMap<>();

        while (!queue.isEmpty()) {
            EdgeDistance current = queue.poll();
            String key = current.getX() + "," + current.getY(); // Обозначаем занятые клетки
            if (visited.containsKey(key)) continue;

            visited.put(key, new Edge(current.getX(), current.getY()));
            if (current.getX() == targetUnit.getX() && current.getY() == targetUnit.getY()) {
                break;
            }

            for (int[] direction : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                int nx = current.getX() + direction[0];
                int ny = current.getY() + direction[1];
                if (nx >= 0 && ny >= 0 && nx < 27 && ny < 21 && grid[nx][ny] != -1) {
                    queue.add(new EdgeDistance(nx, ny, current.getDistance() + 1));
                }
            }
        }

        List<Edge> path = new ArrayList<>();
        String targetKey = targetUnit.getX() + "," + targetUnit.getY();
        while (visited.containsKey(targetKey)) {
            path.add(visited.get(targetKey));
            Edge edge = visited.get(targetKey);
            targetKey = (edge.getX() - attackUnit.getX()) + "," + (edge.getY() - attackUnit.getY());
        }
        Collections.reverse(path);
// Алгоритмическая сложность: O((WIDTH * HEIGHT) log (WIDTH * HEIGHT)), где WIDTH = 27, HEIGHT = 21.

        return path;
    }
}
