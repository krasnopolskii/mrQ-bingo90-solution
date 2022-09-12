package bingo.model;

import java.util.List;

public class Strip {
    private List<Ticket> tickets;

    public Strip(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void sout() {
        tickets.forEach(
                ticket ->  {
                    var rows = ticket.getRows();
                    for(Integer[] row : rows) {
                        for (Integer number : row) {
                            System.out.printf("%3s", number == null ? "_" : number);
                        }
                        System.out.println();
                    }
                    System.out.println();
                }
        );
    }

}
