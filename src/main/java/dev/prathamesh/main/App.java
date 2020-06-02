package dev.prathamesh.main;

import akka.actor.typed.ActorSystem;
import dev.prathamesh.akka.actor.SimpleBehavior;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        akka.actor.typed.ActorSystem<String> actorSystem = ActorSystem.create(SimpleBehavior.create(), "SimpleActorSystem");
        actorSystem.tell("Hello World from Akka!");
        actorSystem.tell("Say hello!");
        actorSystem.tell("Who are you?");

    }
}
