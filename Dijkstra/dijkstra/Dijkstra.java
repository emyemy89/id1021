import java.util.ArrayList;

public class Dijkstra {
    

    // public Path dijkstra(City src, City dst){
    //     // craete queue
    //     PQueue queue=new PQueue();
    //     //done array
    //     DynamicArray<Path> done=new DynamicArray<>();
    //     // add initial path src->src
    //     Path  first= new Path(src,src,0);
    //     queue.enqueue(first);

    //     Path currentP;
    //     // loop until queue empty or we reach the dst city
    //     while(!queue.isEmpty()){
    //         currentP=queue.dequeue(); // get shortest path, allways first in the queue
    //         City currentCity=currentP.getCity();
    //         if(currentCity==dst){
    //             return currentP;
    //         }else{
    //             boolean present=false;
    //             for (int i=0;i<done.getSize();i++){
    //                 if(done.get(i).getCity()==currentCity){    // use get() instead of index accessing
    //                     present=true; 
    //                     break;
    //                 }
    //             }
    //             if(!present){
    //                 done.add(currentP); // add it if not present
    //             }

    //             // get the connections
    //             DynamicArray<Connection> connections= currentCity.getConnections(); // had to change to array list for the next for loop

                
    //             for(int i=0;i<connections.getSize();i++){
    //                 City ngbh=connections.get(i).getN(); // the actual neighbour
    //                 int newDist=currentP.getD()+ connections.get(i).getT();  // get the distance


    //                 boolean shorter=false; //assume there s no shorter path in done, but check
    //                 for(int j=0;j<done.getSize();j++){
    //                     if(done.get(j).getCity()==ngbh ){
    //                         if(done.get(j).getD() < newDist){
    //                             shorter=true;
    //                             break;
    //                         }
    //                     }
    //                 }

    //                 if(!shorter){
    //                     Path newPath=new Path(src,ngbh, newDist);
    //                     queue.enqueue(newPath);
    //                 }


    //             }
    //         }
    //     }
    //     return null;
    // }


    public Path dijkstra(City src, City dst, int mod) {
        // Create queue
        PQueue queue = new PQueue();
        // Done array
        Path[] done = new Path[mod]; // Use array for direct index access
        // Add initial path src->src
        Path first = new Path(src, src, 0);
        queue.enqueue(first);
        Path currentP;
    
        // Loop until queue is empty or we reach the dst city
        while (!queue.isEmpty()) {
            currentP = queue.dequeue(); // Get shortest path, always first in the queue
            City currentCity = currentP.getCity();
            if (currentCity == dst) {
                return currentP;
            } else {
                int cityIndex = hash(currentCity.getN(), mod); // Simplify the indexing
                if (done[cityIndex] == null || done[cityIndex].getD() > currentP.getD()) {
                    done[cityIndex] = currentP; // Only store path if it's shorter or if it doesn't exist
                } else {
                    continue; // Skip processing further if shorter path already exists
                }
    
                // Get the connections
                DynamicArray<Connection> connections = currentCity.getConnections();
                for (int i = 0; i < connections.getSize(); i++) {
                    City ngbh = connections.get(i).getN(); // The actual neighbour
                    int newDist = currentP.getD() + connections.get(i).getT(); // Get the distance


                    // int ngbhIndex = hash(ngbh.getN(), mod);
                    // boolean shorter = done[ngbhIndex] != null && done[ngbhIndex].getD() < newDist; // Assume there’s no shorter path in done, but check
                    

                    boolean shorter = false; // Assume there’s no shorter path in done, but check
                    for (int j = 0; j < done.length; j++) {
                        if (done[j] != null && done[j].getCity().getId() == ngbh.getId()) {
                            if (done[j].getD() < newDist) {
                                shorter = true;
                                break;
                            }
                        }
                    }
                    
                    if (!shorter) {
                        //Path newPath = new Path(src, ngbh, newDist); // Correct constructor call
                        Path newPath = new Path(ngbh, currentCity, newDist);
                        // print full path
                        //newPath.setPreviousPath(currentP);
                        newPath.previousPath = currentP;

                        queue.enqueue(newPath);                       
                    }
                }
            }
        }

        // // // Print the `done` array to debug
        // System.out.println("Done array at end of algorithm:");
        // for (Path p : done) {
        //     if (p != null) {
        //         System.out.println("City: " + p.getCity().getN() + ", Distance: " + p.getD());
        //     }
        // }

        return null;
    }
    

    // public Path dijkstra(City src, City dst, int mod) {
    //     PQueue queue = new PQueue();
    //     Path[] done = new Path[mod];
    //     Path first = new Path(src, src, 0);
    //     queue.enqueue(first);
    //     Path currentP;
    
    //     while (!queue.isEmpty()) {
    //         currentP = queue.dequeue(); // Get shortest path
    //         City currentCity = currentP.getCity();
    //         if (currentCity == dst) {
    //             return currentP;
    //         }
    //         int cityIndex = hash(currentCity.getN(), mod); // Get index using hash function
    //         if (done[cityIndex] == null || done[cityIndex].getD() > currentP.getD()) {
    //             done[cityIndex] = currentP; // Only store path if it's shorter or if it doesn't exist
    //            // System.out.println("Done Updated: " + currentCity.getN() + " : " + currentP.getD());
    //         } else {
    //             continue; // Skip processing further if shorter path already exists
    //         }
    //         DynamicArray<Connection> connections = currentCity.getConnections();
    //         for (int i = 0; i < connections.getSize(); i++) {
    //             City ngbh = connections.get(i).getN(); // Get neighbour
    //             int newDist = currentP.getD() + connections.get(i).getT(); // Calculate distance
    //             int ngbhIndex = hash(ngbh.getN(), mod); // Get index using hash function
    //             if (done[ngbhIndex] == null || done[ngbhIndex].getD() > newDist) {
    //                 Path newPath = new Path(ngbh, currentCity, newDist);
    //                 newPath.setPreviousPath(currentP); // Set the previous path
    //                 queue.enqueue(newPath);
    //                // System.out.println("New Path: " + currentCity.getN() + " -> " + ngbh.getN() + " : " + newDist);
    //             }
    //         }
    //     }
    
    //     // Print the `done` array to debug
    //     System.out.println("Done array at end of algorithm:");
    //     for (Path p : done) {
    //         if (p != null) {
    //             System.out.println("City: " + p.getCity().getN() + ", Distance: " + p.getD());
    //         }
    //     }
    //     return null;
    // }
    
    
    
    
    
    
    private static Integer hash(String name, int mod) {
        int hash = 0;
        for(int i = 0; i < name.length(); i++) {
            hash = (hash * 31 + name.charAt(i)) % mod;
        }
        return hash;
    }
    





}


