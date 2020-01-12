import java.io.*;
import java.util.*;

public class Graph
{
    public int noOfVertices = -1;

    // Adjacency list representing the graph.
    public int[][] edges = null;

    public enum Representation
    {
        AdjacencyList,
        EdgeList,
        Matrix
    }

    /*

    Reading a line:

        String line;
        try
        {
            line = in.readLine();
        }
        catch (IOException e)
        {
            ...
        }


    Splitting into single numbers:

        String[] numbers = line.split("\\s");


    Parsing an int

        int num = Integer.parseInt(numbers[0]);

    */

    public Graph(BufferedReader in, Representation rep)
    {
        switch(rep){
            case AdjacencyList:
                try{
                    String line = in.readLine(); //get number of vertecies in first line
                    int num = Integer.parseInt(line);
                    noOfVertices = num;
                    edges = new int[num][]; //adj is size of vertexes
                    for(int g = 0; g < num; g++){//go to end of file
                        line = in.readLine();
                        String[] numbers = line.split("\\s");
                        if(numbers[0].compareTo("") == 0){
                            edges[g] = new int[0];
                            continue;
                        }
                        int[] sar = new int[numbers.length];
                        for(int i = 0; i < numbers.length; i++){
                            sar[i] = Integer.parseInt(numbers[i]);
                        }
                        edges[g] = sar;
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }catch(java.lang.NullPointerException e){
                    System.out.println("Reached end of file prematurely, Adjacency List");
                    e.printStackTrace();
                }

                break;

            case EdgeList:
                try{
                    String line = in.readLine();
                    String[] numbers = line.split("\\s");
                    int vertices = Integer.parseInt(numbers[0]);
                    noOfVertices = vertices;
                    edges = new int[vertices][]; // edges arrays is number of vertecies
                    ArrayList<Integer>[] inner = new ArrayList[vertices];
                    for(int i = 0; i < vertices; i++){ //set up arraylist
                        inner[i] = new ArrayList<Integer>();
                    }
                    int num = Integer.parseInt(numbers[1]);
                    for(int i = 0; i < num; i++){ // convert to temporary arraylist array
                        line = in.readLine();
                        numbers = line.split("\\s");
                        int val1 = Integer.parseInt(numbers[0]);
                        int val2 = Integer.parseInt(numbers[1]);
                        inner[val1].add(val2);
                    }
                    for(int i = 0; i < vertices; i++){ //convert over to array and add
                        int currentSize = inner[i].size();
                        int[] sv = new int[currentSize];
                        for(int g = 0 ; g < currentSize; g++){
                            sv[g] = inner[i].get(g);
                        }
                        edges[i] = sv;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;

            case Matrix:
                try{
                    String line = in.readLine();
                    int num = Integer.parseInt(line);
                    noOfVertices = num;
                    edges = new int[num][]; //num is square dimension
                    ArrayList<Integer>[] inner = new ArrayList[num];
                    for(int i = 0; i < num; i++){ //set up arraylist
                        inner[i] = new ArrayList<Integer>();
                    }
                    for(int i = 0; i < num; i++){
                        line = in.readLine();
                        String[] numbers = line.split("\\s");
                        for(int g = 0; g < num; g++){//i scrolls over vertices, g scrolls over pairs
                            int current = Integer.parseInt(numbers[g]);
                            if(current == 1){
                                inner[i].add(g);
                            }
                        }
                    }
                    for(int i = 0; i < num; i++){ //convert over to array and add
                        int currentSize = inner[i].size();
                        int[] sv = new int[currentSize];
                        for(int g = 0 ; g < currentSize; g++){
                            sv[g] = inner[i].get(g);
                        }
                        edges[i] = sv;
                    }
                }catch(IOException e){

                }catch(java.lang.NullPointerException e){

                }
                break;

            default:

        }
    }

    public void save(BufferedWriter out, Representation rep)
    {
        switch(rep){
            case AdjacencyList:
                try {
                    out.write(Integer.toString(edges.length));
                    out.newLine();

                    for(int i = 0; i < edges.length; i++){
                        for(int g = 0; g < edges[i].length; g++){
                            out.write(Integer.toString(edges[i][g]) + " ");
                        }
                        out.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case Matrix:
                try {
                    out.write(Integer.toString(edges.length));
                    out.newLine();

                    int[][] matrix = new int[edges.length][edges.length];
                    for(int i = 0; i < edges.length; i++){//fill matrix
                        for(int g = 0; g < edges[i].length; g++){
                            matrix[i][edges[i][g]] = 1;
                        }
                    }
                    for(int i = 0; i < edges.length; i++){//then write matrix
                        for(int g = 0; g < edges.length; g++){
                            out.write(Integer.toString(matrix[i][g]) + " ");
                        }
                        out.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case EdgeList:
                try{
                    out.write(Integer.toString(edges.length) + " ");
                    int count = 0;
                    for(int i = 0; i < edges.length; i++){//count edges
                        count += edges[i].length;
                    }
                    out.write(Integer.toString(count));
                    out.newLine();
                    for(int i = 0; i < edges.length; i++){
                        for(int g = 0; g < edges[i].length; g++){
                            out.write(i + " " + edges[i][g]);
                            out.newLine();
                        }
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
                break;

            default:

        }
    }
}





