package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class Solver {
    private int move = 0;
    private ArrayList<WorldState> list = new ArrayList<>();
    private HashMap<WorldState, Integer> map = new HashMap<>();
    private MinPQ<WorldState> pq = new MinPQ<>(new Comparator<WorldState>() {
        @Override
        public int compare(WorldState o1, WorldState o2) {
            int num1 = o1.estimatedDistanceToGoal() + map.get(o1);
            int num2 = o2.estimatedDistanceToGoal() + map.get(o2);
            return num1 - num2;
        }
    });

    public Solver(WorldState initial){
        BestSearch(initial);
    }

    public void BestSearch(WorldState initial) {
        map.put(initial, 0);
        pq.insert(initial);
        while (!pq.isEmpty()) {
            WorldState w = pq.delMin();
            int nowmove = map.get(w);
            if (nowmove <= move && !pq.isEmpty()){
                int num = move - nowmove + 1;
                for (int i = 0; i < num; i++) {
                    list.remove(list.size() - 1);
                }
            }
            list.add(w);
            move = nowmove;
            if (w.isGoal()) {
                return;
            }
            for (WorldState n : w.neighbors()) {
                if (!map.containsKey(n)) {
                    map.put(n, move + 1);
                    pq.insert(n);
                }
            }
        }
    }

    public int moves(){
        return move;
    }

    public Iterable<WorldState> solution(){
        return new Iterable<WorldState>() {
            @Override
            public Iterator<WorldState> iterator() {
                return list.iterator();
            }
        };
    }
}
