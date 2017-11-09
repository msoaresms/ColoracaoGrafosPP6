import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

final class Vertice implements Comparable<Vertice> {
    public int num;
    public List<Vertice> adjacentes = new ArrayList<Vertice>();
    public int grau;
    public boolean naoCol = true;
    public int cor = -1;

    public Vertice(int pNum){
        this.num = pNum;
    }

    public void calcGrau(){
        grau = adjacentes.size();
    }

    public int compareTo(Vertice pVertice){
        if (this.grau > pVertice.grau){
            return -1;
        } else {
            return 1;
        }
    }
}


final class Grafo {
    public List<Vertice> vertices = new ArrayList<Vertice>();
    public int cores = 0;

    public void calcGrau(){
        for (int i = 0; i < vertices.size(); i++){
            vertices.get(i).calcGrau();
        }
    }

    private void colore(int pCor){
        for (int i = 0; i < vertices.size(); i++){
            if (vertices.get(i).naoCol){
                boolean ok = true;
                for (int j = 0; j < vertices.get(i).adjacentes.size(); j++){
                    if(vertices.get(i).adjacentes.get(j).cor == pCor){
                        ok = false;
                        break;
                    }
                }
                if (ok){
                    vertices.get(i).cor = pCor;
                    vertices.get(i).naoCol = false;
                    colore(pCor);
                }
            }
        }
    }

    public int numCrom(){
        for (int i = 0; i < vertices.size(); i++){
            if (vertices.get(i).naoCol){
                cores++;
                vertices.get(i).naoCol = false;
                vertices.get(i).cor = cores;
                colore(cores);
            }
        }
        return cores;
    }

    public int maximo(){
        int num = this.vertices.get(0).grau;
        //num++;
        return num;
    }
}

final class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Grafo grafo = new Grafo();
        List<Integer> saida = new ArrayList<Integer>();
        int numCrom;
        int max;

        Scanner in = new Scanner(System.in);
        String[] valores = in.nextLine().split("\\s+");
        int v1 = Integer.parseInt(valores[0]); //vertices
        int v2 = Integer.parseInt(valores[1]); //arestas
        int v3 = Integer.parseInt(valores[2]); //testes

        for (int i = 0; i < v1; i++){
            grafo.vertices.add(new Vertice(i));
        }

        for (int i = 0; i < v2; i++){
            String[] linha = in.nextLine().split("\\s+");
            int n1 = (Integer.parseInt(linha[0]))-1;
            int n2 = (Integer.parseInt(linha[1]))-1;

            grafo.vertices.get(n1).adjacentes.add(grafo.vertices.get(n2));
            grafo.vertices.get(n2).adjacentes.add(grafo.vertices.get(n1));
        }

        for (int i = 0; i < v3; i++){
            int num = in.nextInt();
            saida.add(num);
        }

        grafo.calcGrau();
        Collections.sort(grafo.vertices);
        numCrom = grafo.numCrom();
        max = grafo.maximo();


        //System.out.println(numCrom);

        for (int i = 0; i < saida.size(); i++){
            int teste = saida.get(i);
            if (teste >= numCrom && teste <= v1){
                System.out.println("sim");
            } else {
                System.out.println("nao");
            }
        }
    }
}