import java.util.*;
public class Driver { record Card(String rank,String suit){ public String toString(){return rank+" of "+suit;} } public static void main(String[]a){Set<Card> seen=new HashSet<>(); for(Card c:List.of(new Card("Ace","Spades"),new Card("King","Hearts"),new Card("10","Diamonds"),new Card("Ace","Spades"))) if(!seen.add(c)){System.out.println("Duplicate found: "+c);break;} } }
