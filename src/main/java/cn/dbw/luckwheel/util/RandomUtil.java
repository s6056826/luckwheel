package cn.dbw.luckwheel.util;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;

public class RandomUtil {

    public static <T> WeightMeta<T> buildWeightMeta(final Map<T, Integer> weightMap) {
        final int size = weightMap.size();
        Object[] nodes = new Object[size];
        int[] weights = new int[size];
        int index = 0;
        int weightAdder = 0;
        for (Map.Entry<T, Integer> each : weightMap.entrySet()) {
            nodes[index] = each.getKey();
            weights[index++] = (weightAdder = weightAdder + each.getValue());
        }
        return new WeightMeta<T>((T[]) nodes, weights);
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 0);
   map.put("b", 2);
   //map.put("c", 100);
   WeightMeta<String> md = RandomUtil.buildWeightMeta(map);
   String weightRandomUrl = md.random();
        for (int i=0;i<100;i++){
            System.out.println(md.random());
        }
    }
}
