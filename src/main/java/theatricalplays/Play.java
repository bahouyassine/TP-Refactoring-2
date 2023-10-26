package theatricalplays;

public class Play {
  
  public String name;

  public TheaterPlayType type;

  public Play(String name, TheaterPlayType type) {
    if (type != null) {
        this.name = name;
        this.type = type;
    } else {
        throw new IllegalArgumentException("Invalid play type");
    }
}

  public double PlayPrice(double audience){
    double Price = 0;
    switch (this.type) {
      case TRAGEDY:
        Price = Play.TragedyPlayPrice(audience);
        break;
      case COMEDY:
        Price = Play.ComedyPlayPrice(audience);
        break;
      default:
        throw new Error("unknown type: ${play.type}");
    }
    return Price;
  }

  public static double TragedyPlayPrice(double audience){
    return (audience > 30) 
      ? 400 + 10 * (audience - 30)
      : 400 ;
  }
  public static double ComedyPlayPrice(double audience){
    return (audience > 20) 
      ? 300 + 100 + 5 * (audience - 20) +  3 * audience
      : 300 + 3 * audience;
  }
  
  public int CreditIncrease(double audience){
    return (int) ((TheaterPlayType.COMEDY.equals(this.type)) 
    ? Math.max(audience - 30, 0) + Math.floor(audience/5)
    : Math.max(audience - 30, 0));
  }
  
}
