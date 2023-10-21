package theatricalplays;

public class Play {

  public String name;
  public TheaterPlayType type;
  public enum TheaterPlayType {
    TRAGEDY, COMEDY, HISTORY, PASTORAL
  }
  public Play(String name, TheaterPlayType type) {
    // Validate the type of play during object creation
    if (type != null) {
        this.name = name;
        this.type = type;
    } else {
        throw new IllegalArgumentException("Invalid play type");
    }
}


  public double CaluclatePlayAmount(double audience){
    double thisAmount = 0;
    switch (this.type) {
      case TRAGEDY:
        thisAmount = Play.CaluclateTragedyPlayAmount(audience);
        break;
      case COMEDY:
        thisAmount = Play.CaluclateComedyPlayAmount(audience);
        break;
      default:
        throw new Error("unknown type: ${play.type}");
    }
    return thisAmount;
  }

  public static double CaluclateTragedyPlayAmount(double audience){
    return (audience > 30) 
      ? 40000 + 1000 * (audience - 30)
      : 40000 ;
  }
  public static double CaluclateComedyPlayAmount(double audience){
    return (audience > 20) 
      ? 30000 + 10000 + 500 * (audience - 20) +  300 * audience
      : 30000 + 300 * audience;
  }
  
  public int CaluclateVolumeCreditsIncrease(double audience){
    return (int) ((TheaterPlayType.COMEDY.equals(this.type)) 
    ? Math.max(audience - 30, 0) + Math.floor(audience/5)
    : Math.max(audience - 30, 0));
  }
  
}
