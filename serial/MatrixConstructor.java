public class MatrixFiller {
  public void constuct(){
    if(GOL.matrix.length == 0){
        buildMatrix();
    }
    Random generator = new Random();
    for (int x = 0; x < matrix.length; x++) {
      for (int y = 0; y < matrix[x].length; y++) {
        matrix[x][y] = generator.nextInt(2);
      }
    }
  return matrix;
  }

  private void buildMatrix() {

    Scanner scan = new Scanner(System.in);
    System.out.println("Please enter the amount of rows.");
    int rows = scan.nextInt();
    System.out.println("Please enter the amount of columns.");
    int colmns = scan.nextInt();

    GOL.matrix = new int[rows][colmns];
  }
}
