import java.util.Objects;
public class Card{
   private String rank;
    private String suit;
   public Card(String rank,String suit){
        this.rank=rank;
        this.suit=suit;
    }
  public String toString(){
        return rank+" of "+suit;
    }
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(obj==null||getClass()!=obj.getClass()){
            return false;
        }
        Card c=(Card)obj;
        return rank.equals(c.rank)&&suit.equals(c.suit);
    }
    public int hashCode(){
        return Objects.hash(rank,suit);
    }
    public static boolean isDuplicate(Card[] cards,int count,Card card){
        for(int i=0;i<count;i++){
            if(card.equals(cards[i])){
                return true;
            }     }
        return false;
    }
    public static void main(String[] args){

        Card[] cards=new Card[5];
        int count=0;
        Card c;
        c=new Card("King","Diamonds");
        if(!isDuplicate(cards,count,c)){
            cards[count++]=c;
        }
      c=new Card("Queen","Hearts");
        if(!isDuplicate(cards,count,c)){
            cards[count++]=c;
        }
   c=new Card("10","Clubs");
        if(!isDuplicate(cards,count,c)){
            cards[count++]=c;
        }
   c=new Card("King","Diamonds");
        if(isDuplicate(cards,count,c)){
            System.out.println("Duplicate found: "+c);
        }else{
            cards[count++]=c;
        }
     c=new Card("7","Spades");
        if(!isDuplicate(cards,count,c)){
            cards[count++]=c;
        }  } }
