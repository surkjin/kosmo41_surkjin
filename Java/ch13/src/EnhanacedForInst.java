//인스턴스 배열 대상 for-each문의 예

public class EnhanacedForInst {

	public static void main(String[] args) {

		Box[] ar = new Box[5];
		ar[0] = new Box(101, "Cofee");
		ar[1] = new Box(202, "Computer");
		ar[2] = new Box(303, "Apple");
		ar[3] = new Box(404, "Dress");
		ar[4] = new Box(505, "Fairy-tale book");
		
		for (Box e: ar) {
			if(e.getBoxNum() == 505)
				System.out.println(e);
		}
	}

}
