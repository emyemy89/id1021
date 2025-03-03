public class ArrayUtil{

    public static void print(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static int[] removeOdd(int[] arr){
        int count=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]%2==0){
                count++;
            }
        }
        int[] evenArray=new int[count];
        int j=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]%2==0){
                evenArray[j]=arr[i];
                j++;
            }
        }
        return evenArray;        
    }

    public static int[] reverse(int[] arr){
        int i=0;
        int j=arr.length-1;
        int temp=0;
        while(i<j){
            temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
            i++;
            j--;
        }
        return arr;
    }

    public static int findMin(int[] arr){
        int min=arr[0];
        for(int i=0;i<arr.length;i++){
            if(min>arr[i]){
                min=arr[i];
            }
        }
        return min;
    }
    // this method finds the missing number in an array
    // for ex in this example{1,3,4,5,6}, it will return 2
    public static int missingNumber( int[] arr){
        int n=arr.length+1;
        int sum=0;
        sum=n*(n+1)/2;
    
        for(int i=0;i<arr.length;i++){
            sum-=arr[i];
        }
        return sum;
    
    }

    public boolean isPolindrome (String word){
        char[] arr=word.toCharArray();
        int i=0;
        int j=arr.length-1;
        while(i<j){
            if(arr[i]!=arr[j]){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args){
        int[] arr={1,3,4,5,6,7,8,9,10};

        int[] result1=removeOdd(arr);
        print(result1);

        print(reverse(arr));

        int minimumEl=findMin(arr);
        System.out.println(minimumEl);

       System.out.println(missingNumber(arr));

       ArrayUtil name1= new ArrayUtil();
       System.out.println(name1.isPolindrome("madam"));

    }
}
