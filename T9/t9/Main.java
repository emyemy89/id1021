

// public class Main {
//     public static void main(String[] args) {
//         T9 t9 = new T9();
//         String filePath = "kelly.txt"; // Adjust the path if needed

//         // Step 1: Read words from the file and populate the T9 tree
//         try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//             String line;
//             while ((line = br.readLine()) != null) {
//                 t9.add(line.trim()); // Add each word to the T9 tree
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }

//         // Step 2: Test decoding a key sequence
//         String keySequence = "23434"; // Example key sequence
//         ArrayList<String> possibleWords = t9.decode(keySequence);

//         // Step 3: Print out the possible words
//         System.out.println("Possible words for the key sequence \"" + keySequence + "\":");
//         for (String word : possibleWords) {
//             System.out.println(word);
//         }
//     }
// }

