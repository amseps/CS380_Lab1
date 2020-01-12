import java.io.*;

public class Driver {
    public static void main(String args[]){
        try {
            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);

            Graph g = new Graph(br, Graph.Representation.Matrix);
            //Graph g = new Graph(br, Graph.Representation.EdgeList);
            //Graph g = new Graph(br, Graph.Representation.AdjacencyList);

            br.close();
            fr.close();

            FileWriter fw = new FileWriter("output.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            g.save(bw, Graph.Representation.Matrix);
           // g.save(bw, Graph.Representation.AdjacencyList);
            //g.save(bw, Graph.Representation.EdgeList);

            bw.close();
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Succ");
    }
}
