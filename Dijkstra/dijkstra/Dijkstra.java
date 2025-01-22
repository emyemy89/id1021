import java.util.ArrayList;

public class Dijkstra {
    

    public Path dijkstra(City src, City dst, int mod) {
        PQueue queue = new PQueue();
        // Done array
        Path[] done = new Path[mod];
        // Add initial path src->src
        Path first = new Path(src, src, 0);
        queue.enqueue(first);
        Path currentP;
    
        while (!queue.isEmpty()) {
            currentP = queue.dequeue();
            City currentCity = currentP.getCity();
            if (currentCity == dst) {
                return currentP;
            } else {
                int cityIndex = hash(currentCity.getN(), mod); 
                if (done[cityIndex] == null || done[cityIndex].getD() > currentP.getD()) {
                    done[cityIndex] = currentP; 
                } else {
                    continue; 
                }
    
                DynamicArray<Connection> connections = currentCity.getConnections();
                for (int i = 0; i < connections.getSize(); i++) {
                    City ngbh = connections.get(i).getN(); 
                    int newDist = currentP.getD() + connections.get(i).getT(); 


                    // int ngbhIndex = hash(ngbh.getN(), mod);
                    // boolean shorter = done[ngbhIndex] != null && done[ngbhIndex].getD() < newDist; 
                    

                    boolean shorter = false; 
                    for (int j = 0; j < done.length; j++) {
                        if (done[j] != null && done[j].getCity().getId() == ngbh.getId()) {
                            if (done[j].getD() < newDist) {
                                shorter = true;
                                break;
                            }
                        }
                    }
                    
                    if (!shorter) {
                        Path newPath = new Path(ngbh, currentCity, newDist);
                        //newPath.setPreviousPath(currentP);
                        newPath.previousPath = currentP;

                        queue.enqueue(newPath);                       
                    }
                }
            }
        }

        return null;
    }
    

    
    
    
    
    private static Integer hash(String name, int mod) {
        int hash = 0;
        for(int i = 0; i < name.length(); i++) {
            hash = (hash * 31 + name.charAt(i)) % mod;
        }
        return hash;
    }
    



}


