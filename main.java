import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class main {

    private static final List<Point> points = new ArrayList<>();
    private static final String path = "input.txt";
    private static int sum = 0;
    private static int n;
    private static final List<List<Integer>> output_list = new ArrayList<>();
    private static int [][] graph;


    public static void main(String[] args) {
        readData();
        prim();

        String output = "";
        for(List<Integer> component : output_list){
            if(component.isEmpty())
                continue;
            component.sort((i1, i2) ->{
                if(i1 > i2)
                    return 1;
                if(i1 < i2)
                    return -1;
                return 0;
            } );
            for(int node : component){
                output += node + " ";
            }
            output += "0\n";
        }
        output += sum;
        writeData(output);
    }


    public static int minKey(int[] key, Boolean[] mstSet)
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < n; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        return min_index;
    }


    public static void prim()
    {
        int[] parent = new int[n];
        int[] key = new int[n];
        Boolean[] mstSet = new Boolean[n];
        for (int i = 0; i < n; i++)
            output_list.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < n - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
        for (int i = 1; i < n; i++) {
            sum += graph[i][parent[i]];
            output_list.get(parent[i]).add(i + 1);
            output_list.get(i).add(parent[i] + 1);
        }
    }



    private static void readData(){
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(main.path), StandardCharsets.UTF_8))){
            String line = reader.readLine();
            n = Integer.parseInt(line);
            graph = new int[n][n];
            int i = 1;
            while (i < n+1 && (line = reader.readLine()) != null) {
                String[] coordinates = line.split(" ");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                points.add(new Point(x, y, i));
                i++;
            }
            for (int j = 0; j < points.size(); j++){
                for (int k = 0; k < points.size(); k++){
                    graph[j][k] = Math.abs(points.get(j).get_x_coordinate() - points.get(k).get_x_coordinate()) +
                            Math.abs(points.get(j).get_y_coordinate() - points.get(k).get_y_coordinate());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeData(String output){
        try(FileWriter writer = new FileWriter("output.txt", false))
        {
            writer.write(output);
            writer.flush();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
