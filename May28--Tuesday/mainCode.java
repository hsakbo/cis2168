public class mainCode{
    private int i;
    public mainCode(int i){
	this.i = i; //garbage collection?
    }
    public mainCode(){
    }
    
    public static void print(){
	mainCode o = new mainCode();
	System.out.println(o.toString());
    }
    
    public static void main(String[] args){
	System.out.println("hello world");
	print();
	//casting in polymorphism is complicated.
	int c1, c2 = 0;
	
	//c1 == c2 and c1.equals(c2)
	
    }
}
