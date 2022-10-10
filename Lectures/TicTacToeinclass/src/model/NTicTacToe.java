package model;

public class NTicTacToe implements ITicTacToe {
  private final int width;
  private final int height;
  private final int winGoal;
  private final Player [] players;

//  public NTicTacToe(int width, int height){
//    this(width, height, 4, new Player[]{Player.O, Player.X});
//  }
//
//  public NTicTacToe() {
//    this(5, 5, 4, new Player[]{Player.O, Player.X});
//  }

  private NTicTacToe(int width, int height, int winGoal, Player [] players){
    if (width < 0|| height <0 || winGoal < 1 || players == null) {

    }
    this.width = width;
    this.height = height;
    this.winGoal = winGoal;
    this.players = players;
  }
  @Override
  public void move(int x, int y) {

  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public Player nextPlayer() {
    return null;
  }

  @Override
  public String winner() {
    return null;
  }

  @Override
  public boolean hasWinner() {
    return false;
  }

  @Override
  public Player getPlayerAt(int x, int y) {
    return null;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  // static so we dont need to make a builder to make a game
  public static class NConnectBuilder {
    private int b_width;
    private int b_height;
    private int b_winGoal;
    private Player [] b_players;

    public NConnectBuilder(int width, int height, int winGoal, Player[] players){
      this.b_width = width;
      this.b_height = height;
      this.b_winGoal = winGoal;
      this.b_players = players;
    }

    public NConnectBuilder(){
      this.b_width =5;
      this.b_height =5;
      this.b_winGoal = 4;
      this.b_players = new Player[]{Player.O, Player.X};
    }

    NConnectBuilder setWidth(int value) {
      this.b_width = value;
      return this;
    }

    NConnectBuilder setHeight(int value ) {
      this.b_height = value;
      return this;
    }

    NConnectBuilder setWinGoal(int value) {
      this.b_winGoal = value;
    }

    NConnectBuilder setPlayers(Player ... players) {
      this.b_players = players;
      return this;
    }

    public NTicTacToe makeGame(){
      return new NTicTacToe(this.b_width, this.b_height, this.b_winGoal, this.b_players);
    }
  }
}
