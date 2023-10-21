package theatricalplays;

public class Play {

  public String name;
  public String type;

  public Play(String name, String type) {
    this.name = name;
    this.type = type;
  }

  public static int CaluclateTragedyPlayAmount(int audience){
    int thisAmount = 40000;
    if (audience > 30) {
      thisAmount += 1000 * (audience - 30);
    }
    return thisAmount;
  }
  public static int CaluclateComedyPlayAmount(int audience){
    int thisAmount = 30000;
    if (audience > 20) {
      thisAmount += 10000 + 500 * (audience - 20);
    }
    thisAmount += 300 * audience;
    return thisAmount;
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
}
