package theatricalplays;

public class Play {

  public String name;
  public String type;

  public Play(String name, String type) {
    this.name = name;
    this.type = type;
  }

  public static int CaluclateTragedyPlayAmount(int audience){
    return (audience > 30) 
      ? 40000 + 1000 * (audience - 30)
      : 40000 ;
  }
  public static int CaluclateComedyPlayAmount(int audience){
    return (audience > 20) 
      ? 30000 + 10000 + 500 * (audience - 20) +  300 * audience
      : 30000 + 300 * audience;
  }

  public int CaluclatePlayAmount(int audience){
    int thisAmount = 0;
    switch (this.type) {
      case "tragedy":
        thisAmount = Play.CaluclateTragedyPlayAmount(audience);
        break;
      case "comedy":
        thisAmount = Play.CaluclateComedyPlayAmount(audience);
        break;
      default:
        throw new Error("unknown type: ${play.type}");
    }
    return thisAmount;
  }
  public int CaluclateVolumeCreditsIncrease(int audience){
    return (int) (("comedy".equals(this.type)) 
    ? Math.max(audience - 30, 0) + Math.floor(audience/5)
    : Math.max(audience - 30, 0));
  }
  
}
