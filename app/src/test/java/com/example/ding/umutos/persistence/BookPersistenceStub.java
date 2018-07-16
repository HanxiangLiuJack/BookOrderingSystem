package com.example.ding.umutos.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import com.example.ding.umutos.objects.Book;


public class BookPersistenceStub implements BookPersistence {

    private List<Book> books;

    public BookPersistenceStub() {
        this.books = new ArrayList<>();
        books.add(new Book("Agile Development", "James Shore",1,"The Art of Agile Development contains practical guidance for anyone considering or applying agile development for building valuable software. Plenty of books describe what agile development is or why it helps software projects succeed, but very few combine information for developers, managers, testers, and customers into a single package that they can apply directly.","Family and consumer science",33.86,"Tianhua Xu"));
        books.add(new Book("Microeconomics", "Christopher T.S. Ragan",2,"An indispensable reference for students enrolled in any business and economics program, Ragan: Economics builds on a rich legacy of success in teaching and learning. Ragan: Economics provides students with an introduction to the major issues facing the worlds economies, to the methods that economists use to study those issues, and to the policy problems that those issues create.","Business",34.95,"Tianhua Xu"));
        books.add(new Book("The Civil Engineering ", " W.F. Chen J.Y. Richard Liew",3,"The Civil Engineering Handbook provides extensive coverage of the major areas of civil engineering. Divided into eight comprehensive sections, the handbook covers:Construction. Environmental Engineering; Geotechnical Engineering; Hydraulics; Materials Engineering; Structural Engineering; Surveying Engineering; Transportation Engineering.","Engineering and technology",163.34,"Tianhua Xu"));
        books.add(new Book("Essential Scrum"," Kenneth S. Rubin",4,"If you want to use Scrum to develop innovative products and services that delight your customers, Essential Scrum is the complete, single-source reference you’ve been searching for. Leading Scrum coach and trainer Kenny Rubin illuminates the values, principles, and practices of Scrum, and describes flexible, proven approaches that can help you implement it far more effectively.","Family and consumer science",11.12,"Tianhua Xu"));
        books.add(new Book("Economic and Social History of Medieval Europe","Henri Pirenne",5,"2014 Reprint of 1937 Edition. Full facsimile of the original edition, not reproduced with Optical Recognition Software. Henri Pirenne's reputation today rests on three contributions to European history: for what has become known as the Pirenne Thesis, concerning the origins of the Middle Ages in reactive state formation and shifts in trade; for a distinctive view of Belgium's medieval history; and for his model of the development of the medieval city. Pirenne argued that profound social, economic, cultural, and religious movements in the long-term resulted from equally profound underlying causes, and this attitude influenced Marc Bloch and the outlook of the French Annales School of social history. Though Pirenne had his opponents who disagreed on essential points, recent historians of the Middle Ages usually take Pirenne's main theses, however much they are modified, as starting points.","Business",9.95,"Tianhua Xu"));
        books.add(new Book("An Economic and Social History of Later Medieval Europe","Steven A. Epstein",6,"This book examines the most important themes in European social and economic history from the beginning of growth around the year 1000 to the first wave of global exchange in the 1490s. These five hundred years witnessed the rise of economic systems, such as capitalism, and the social theories that would have a profound influence on the rest of the world over the next five centuries. The basic story, the human search for food, clothing, and shelter in a world of violence and scarcity, is a familiar one, and the work and daily routines of ordinary women and men are the focus of this volume. Surveying the full extent of Europe, from east to west and north to south, Steven Epstein illuminates family life, economic and social thought, war, technologies, and other major themes while giving equal attention to developments in trade, crafts, and agriculture.","Business",11.66,"Tianhua Xu"));
        books.add(new Book("The Middle Ages","Jeffrey L. Singman",7,"We consider the Middle Ages barbaric, yet the period furnished some of our most enduring icons, including King Arthur's Round Table, knights in shining armor, and the idealized noblewoman. In this vivid history of the time, the medieval world comes to life in all its rich daily experience. Find out what people's beds were like, how often they washed, what they wore, what they cooked, how they worked, how they entertained themselves, how they wed, and what life was like in a medieval village, castle, or monastery.","Journalism, media studies and communication",30.22,"Tianhua Xu"));
        books.add(new Book("The Guilty Wife","Elle Croft",8,"When Bethany's lover is brutally murdered, she has to hide her grief from everyone.But someone knows her secret. And then one day the threats begin.With an ever-growing pile of evidence pointing to her as the murderer, the only way she can protect her secrets is to prove her innocence. And that means tracking down a killer.An unbelievably gripping game of cat and mouse - with a twist you'll never see coming. Fans of The Child by Fiona Barton, Close to Home by Cara Hunter, Come a Little Closer by Rachel Abbott and I Am Watching You by Tessa Driscoll will love The Guilty Wife.","Human physical performance and recreation",22.45,"Tianhua Xu"));
        books.add(new Book("One Way","S. J. Morden",9,"Frank Kittridge is serving life for murdering his son's drug dealer, so when he's offered a deal by Xenosystems Operations - the corporation that owns the prison - he takes it. He's been selected to help build the first permanent base on Mars. Unfortunately, his crewmates are just as guilty of their crimes as he is. ","Human physical performance and recreation",33.22,"Tianhua Xu"));
        books.add(new Book("The Arrangement","Sarah Dunn",10,"Lucy and Owen, ambitious, thoroughly-therapized New Yorkers, have taken the plunge, trading in their crazy life in a cramped apartment for Beekman, a bucolic Hudson Valley exurb. They've got a two hundred year-old house, an autistic son obsessed with the Titanic, and 17 chickens, at last count. It's the kind of paradise where stay-at-home moms team up to cook the school's hot lunch, dads grill grass-fed burgers, and, as Lucy observes.","Human physical performance and recreation",22.55,"Hanxiang Liu"));
    }
    
    
    //Print Book List
    @Override
    public List<Book> getBookSequential() {
        return books;
    }

    @Override
    public Book insertBook(Book currentBook) {
        books.add(currentBook);
        return currentBook;
    }

    @Override
    public Book updateBook(Book currentBook) {
        int index;

        index = books.indexOf(currentBook);
        if (index >= 0)
        {
            books.get(index).setName(currentBook.getName());
            books.get(index).setAuthor(currentBook.getAuthor());
            books.get(index).setPicture(currentBook.getPicture());
            books.get(index).setDescription(currentBook.getDescription());
            books.get(index).setCategory(currentBook.getCategory());
            books.get(index).setPrice(currentBook.getPrice());
        
        }
        return currentBook;
    }
    
    @Override
    public Book searchBook(int id) {
        Book searchBook = null;

        for (int i = 0; i < books.size()&&id>=0; i++) {
            if(books.get(i).getBookID()==id)
                searchBook = books.get(i);
        }
        return searchBook;
    }
    
    @Override
    public List<Book> getUserBookSequential(String userName) {
        List<Book> newBooks = new ArrayList<>();
        for (int i = 0; i < books.size();i++){
            if(books.get(i).getOwner().equals(userName)){
                newBooks.add(books.get(i));
            }
        }
        return newBooks;
    }


    @Override
    public void deleteBook(int id) {
        int index;
        
        index=books.indexOf(searchBook(id));
        if(index>=0)
            books.remove(index);
    }

    @Override
    public List<Book> getBookCategorySequential(String category) {
        return null;
    }

    @Override
    public List<Book> searchKeyword(String keyword) {
        return null;
    }

}
