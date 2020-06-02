package dev.prathamesh.akka.actor;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class SimpleBehavior extends AbstractBehavior<String> {

    private SimpleBehavior(ActorContext<String> context){
        super(context);
    }

    public static Behavior<String> create(){
        return Behaviors.setup(context -> new SimpleBehavior(context));
    }

    @Override
    public Receive<String> createReceive() {
        return newReceiveBuilder().
                onMessageEquals("Say hello!", () -> {
                    System.out.printf("\n Hello!");
                    return  this;
                })
                .onMessageEquals("Who are you?", () -> {
                    System.out.printf("\n I am %s", getContext().getSelf().path());
                    return  this;
                 })
                .onMessageEquals("Create a Child", () -> {
                    ActorRef<String> secondActor = getContext().spawn(SimpleBehavior.create(), "SecondActor");
                    secondActor.tell("Who are you?");
                    return  this;
                })
                .onAnyMessage(
                    message -> {
                        System.out.printf("\n Message Received as %s ", message);
                    return this;
                }).build();
    }
}
