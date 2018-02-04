package com.ibeka.JokeApp.service;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.stereotype.Service;

@Service
public class JokeServicesImpl implements JokeServices{

    private final ChuckNorrisQuotes chuckNorrisQuotes;

//    JokeServices jokeServices;

    /*
     * Creating a constructor with argument in this case makes no sense since you would not expect the class to enter an argument.
     */
//    public JokeServicesImpl(JokeServices jokeServices) {
//        this.jokeServices = jokeServices;
//    }

    /*
     * Initializing variables inside a no-arg constructor is useful in this case.
     * This kind of initialization should be used for databases too. You don't need the customer to initialize the collection type you use for data
     */
    public JokeServicesImpl(){
        this.chuckNorrisQuotes = new ChuckNorrisQuotes();
    }

    //This uses the ChuckConfiguration class.
    /*
     * This way be useful in cases were you are using a class from an external source. This s good even though you are using a concrete class variable type ChuckNorrisQuotes
     */
    public JokeServicesImpl(ChuckNorrisQuotes chuckNorrisQuotes){
        this.chuckNorrisQuotes = new ChuckNorrisQuotes();
    }

    @Override
    public String getJokes() {
        return chuckNorrisQuotes.getRandomQuote();
    }
}
