//Defining the pieces and color
public class Piece {
  private String name;
  private String color;

//The class has three methods and has a constructor that takes in a parameter of type String named color. 
  
  public Piece(String color) {
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public String getColor() {
    return color;
  }

  public void setName(String name) {
    this.name = name;
  }
}
