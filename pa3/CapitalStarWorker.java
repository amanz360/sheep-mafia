package pa3;

// Import declarations generated by the SALSA compiler, do not modify.
import java.io.IOException;
import java.util.Vector;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;

import salsa.language.Actor;
import salsa.language.ActorReference;
import salsa.language.Message;
import salsa.language.RunTime;
import salsa.language.ServiceFactory;
import gc.WeakReference;
import salsa.language.Token;
import salsa.language.exceptions.*;
import salsa.language.exceptions.CurrentContinuationException;

import salsa.language.UniversalActor;

import salsa.naming.UAN;
import salsa.naming.UAL;
import salsa.naming.MalformedUALException;
import salsa.naming.MalformedUANException;

import salsa.resources.SystemService;

import salsa.resources.ActorService;

// End SALSA compiler generated import delcarations.

import java.util.Arrays;

public class CapitalStarWorker extends UniversalActor  {
	public static void main(String args[]) {
		UAN uan = null;
		UAL ual = null;
		if (System.getProperty("uan") != null) {
			uan = new UAN( System.getProperty("uan") );
			ServiceFactory.getTheater();
			RunTime.receivedUniversalActor();
		}
		if (System.getProperty("ual") != null) {
			ual = new UAL( System.getProperty("ual") );

			if (uan == null) {
				System.err.println("Actor Creation Error:");
				System.err.println("	uan: " + uan);
				System.err.println("	ual: " + ual);
				System.err.println("	Identifier: " + System.getProperty("identifier"));
				System.err.println("	Cannot specify an actor to have a ual at runtime without a uan.");
				System.err.println("	To give an actor a specific ual at runtime, use the identifier system property.");
				System.exit(0);
			}
			RunTime.receivedUniversalActor();
		}
		if (System.getProperty("identifier") != null) {
			if (ual != null) {
				System.err.println("Actor Creation Error:");
				System.err.println("	uan: " + uan);
				System.err.println("	ual: " + ual);
				System.err.println("	Identifier: " + System.getProperty("identifier"));
				System.err.println("	Cannot specify an identifier and a ual with system properties when creating an actor.");
				System.exit(0);
			}
			ual = new UAL( ServiceFactory.getTheater().getLocation() + System.getProperty("identifier"));
		}
		RunTime.receivedMessage();
		CapitalStarWorker instance = (CapitalStarWorker)new CapitalStarWorker(uan, ual,null).construct();
		gc.WeakReference instanceRef=new gc.WeakReference(uan,ual);
		{
			Object[] _arguments = { args };

			//preAct() for local actor creation
			//act() for remote actor creation
			if (ual != null && !ual.getLocation().equals(ServiceFactory.getTheater().getLocation())) {instance.send( new Message(instanceRef, instanceRef, "act", _arguments, false) );}
			else {instance.send( new Message(instanceRef, instanceRef, "preAct", _arguments, false) );}
		}
		RunTime.finishedProcessingMessage();
	}

	public static ActorReference getReferenceByName(UAN uan)	{ return new CapitalStarWorker(false, uan); }
	public static ActorReference getReferenceByName(String uan)	{ return CapitalStarWorker.getReferenceByName(new UAN(uan)); }
	public static ActorReference getReferenceByLocation(UAL ual)	{ return new CapitalStarWorker(false, ual); }

	public static ActorReference getReferenceByLocation(String ual)	{ return CapitalStarWorker.getReferenceByLocation(new UAL(ual)); }
	public CapitalStarWorker(boolean o, UAN __uan)	{ super(false,__uan); }
	public CapitalStarWorker(boolean o, UAL __ual)	{ super(false,__ual); }
	public CapitalStarWorker(UAN __uan,UniversalActor.State sourceActor)	{ this(__uan, null, sourceActor); }
	public CapitalStarWorker(UAL __ual,UniversalActor.State sourceActor)	{ this(null, __ual, sourceActor); }
	public CapitalStarWorker(UniversalActor.State sourceActor)		{ this(null, null, sourceActor);  }
	public CapitalStarWorker()		{  }
	public CapitalStarWorker(UAN __uan, UAL __ual, Object obj) {
		//decide the type of sourceActor
		//if obj is null, the actor must be the startup actor.
		//if obj is an actorReference, this actor is created by a remote actor

		if (obj instanceof UniversalActor.State || obj==null) {
			  UniversalActor.State sourceActor;
			  if (obj!=null) { sourceActor=(UniversalActor.State) obj;}
			  else {sourceActor=null;}

			  //remote creation message sent to a remote system service.
			  if (__ual != null && !__ual.getLocation().equals(ServiceFactory.getTheater().getLocation())) {
			    WeakReference sourceRef;
			    if (sourceActor!=null && sourceActor.getUAL() != null) {sourceRef = new WeakReference(sourceActor.getUAN(),sourceActor.getUAL());}
			    else {sourceRef = null;}
			    if (sourceActor != null) {
			      if (__uan != null) {sourceActor.getActorMemory().getForwardList().putReference(__uan);}
			      else if (__ual!=null) {sourceActor.getActorMemory().getForwardList().putReference(__ual);}

			      //update the source of this actor reference
			      setSource(sourceActor.getUAN(), sourceActor.getUAL());
			      activateGC();
			    }
			    createRemotely(__uan, __ual, "pa3.CapitalStarWorker", sourceRef);
			  }

			  // local creation
			  else {
			    State state = new State(__uan, __ual);

			    //assume the reference is weak
			    muteGC();

			    //the source actor is  the startup actor
			    if (sourceActor == null) {
			      state.getActorMemory().getInverseList().putInverseReference("rmsp://me");
			    }

			    //the souce actor is a normal actor
			    else if (sourceActor instanceof UniversalActor.State) {

			      // this reference is part of garbage collection
			      activateGC();

			      //update the source of this actor reference
			      setSource(sourceActor.getUAN(), sourceActor.getUAL());

			      /* Garbage collection registration:
			       * register 'this reference' in sourceActor's forward list @
			       * register 'this reference' in the forward acquaintance's inverse list
			       */
			      String inverseRefString=null;
			      if (sourceActor.getUAN()!=null) {inverseRefString=sourceActor.getUAN().toString();}
			      else if (sourceActor.getUAL()!=null) {inverseRefString=sourceActor.getUAL().toString();}
			      if (__uan != null) {sourceActor.getActorMemory().getForwardList().putReference(__uan);}
			      else if (__ual != null) {sourceActor.getActorMemory().getForwardList().putReference(__ual);}
			      else {sourceActor.getActorMemory().getForwardList().putReference(state.getUAL());}

			      //put the inverse reference information in the actormemory
			      if (inverseRefString!=null) state.getActorMemory().getInverseList().putInverseReference(inverseRefString);
			    }
			    state.updateSelf(this);
			    ServiceFactory.getNaming().setEntry(state.getUAN(), state.getUAL(), state);
			    if (getUAN() != null) ServiceFactory.getNaming().update(state.getUAN(), state.getUAL());
			  }
		}

		//creation invoked by a remote message
		else if (obj instanceof ActorReference) {
			  ActorReference sourceRef= (ActorReference) obj;
			  State state = new State(__uan, __ual);
			  muteGC();
			  state.getActorMemory().getInverseList().putInverseReference("rmsp://me");
			  if (sourceRef.getUAN() != null) {state.getActorMemory().getInverseList().putInverseReference(sourceRef.getUAN());}
			  else if (sourceRef.getUAL() != null) {state.getActorMemory().getInverseList().putInverseReference(sourceRef.getUAL());}
			  state.updateSelf(this);
			  ServiceFactory.getNaming().setEntry(state.getUAN(), state.getUAL(),state);
			  if (getUAN() != null) ServiceFactory.getNaming().update(state.getUAN(), state.getUAL());
		}
	}

	public UniversalActor construct (Star _stars[], int _range, int _workerNum, int _overflow) {
		Object[] __arguments = { _stars, new Integer(_range), new Integer(_workerNum), new Integer(_overflow) };
		this.send( new Message(this, this, "construct", __arguments, null, null) );
		return this;
	}

	public UniversalActor construct() {
		Object[] __arguments = { };
		this.send( new Message(this, this, "construct", __arguments, null, null) );
		return this;
	}

	public class State extends UniversalActor .State {
		public CapitalStarWorker self;
		public void updateSelf(ActorReference actorReference) {
			((CapitalStarWorker)actorReference).setUAL(getUAL());
			((CapitalStarWorker)actorReference).setUAN(getUAN());
			self = new CapitalStarWorker(false,getUAL());
			self.setUAN(getUAN());
			self.setUAL(getUAL());
			self.activateGC();
		}

		public State() {
			this(null, null);
		}

		public State(UAN __uan, UAL __ual) {
			super(__uan, __ual);
			addClassName( "pa3.CapitalStarWorker$State" );
			addMethodsForClasses();
		}

		public void construct() {}

		public void process(Message message) {
			Method[] matches = getMatches(message.getMethodName());
			Object returnValue = null;
			Exception exception = null;

			if (matches != null) {
				if (!message.getMethodName().equals("die")) {activateArgsGC(message);}
				for (int i = 0; i < matches.length; i++) {
					try {
						if (matches[i].getParameterTypes().length != message.getArguments().length) continue;
						returnValue = matches[i].invoke(this, message.getArguments());
					} catch (Exception e) {
						if (e.getCause() instanceof CurrentContinuationException) {
							sendGeneratedMessages();
							return;
						} else if (e instanceof InvocationTargetException) {
							sendGeneratedMessages();
							exception = (Exception)e.getCause();
							break;
						} else {
							continue;
						}
					}
					sendGeneratedMessages();
					currentMessage.resolveContinuations(returnValue);
					return;
				}
			}

			System.err.println("Message processing exception:");
			if (message.getSource() != null) {
				System.err.println("\tSent by: " + message.getSource().toString());
			} else System.err.println("\tSent by: unknown");
			System.err.println("\tReceived by actor: " + toString());
			System.err.println("\tMessage: " + message.toString());
			if (exception == null) {
				if (matches == null) {
					System.err.println("\tNo methods with the same name found.");
					return;
				}
				System.err.println("\tDid not match any of the following: ");
				for (int i = 0; i < matches.length; i++) {
					System.err.print("\t\tMethod: " + matches[i].getName() + "( ");
					Class[] parTypes = matches[i].getParameterTypes();
					for (int j = 0; j < parTypes.length; j++) {
						System.err.print(parTypes[j].getName() + " ");
					}
					System.err.println(")");
				}
			} else {
				System.err.println("\tThrew exception: " + exception);
				exception.printStackTrace();
			}
		}

		Star stars[];
		Double x, y, z;
		Double dist;
		int workerNum = 0;
		int range = 0;
		int overflow = 0;
		void construct(Star _stars[], int _range, int _workerNum, int _overflow){
			stars = _stars;
						{
				Token token_2_0 = new Token();
				// stars[0]<-getAvgDist()
				{
					Object _arguments[] = {  };
					Message message = new Message( self, stars[0], "getAvgDist", _arguments, null, token_2_0 );
					__messages.add( message );
				}
				// setDist(token)
				{
					Object _arguments[] = { token_2_0 };
					Message message = new Message( self, self, "setDist", _arguments, token_2_0, null );
					__messages.add( message );
				}
			}
						{
				Token token_2_0 = new Token();
				// stars[0]<-getCoord()
				{
					Object _arguments[] = {  };
					Message message = new Message( self, stars[0], "getCoord", _arguments, null, token_2_0 );
					__messages.add( message );
				}
				// setCoord(token)
				{
					Object _arguments[] = { token_2_0 };
					Message message = new Message( self, self, "setCoord", _arguments, token_2_0, null );
					__messages.add( message );
				}
			}
			range = _range;
			workerNum = _workerNum;
			overflow = _overflow;
		}
		public void setDist(Double _dist) {
			dist = _dist;
		}
		public void setCoord(Double coord[]) {
			x = coord[0];
			y = coord[1];
			z = coord[2];
		}
		public int findBest(Object arr[]) {
			int index = -1;
			for (int i = 0; i<arr.length; i++){
				if ((Double)arr[i]<dist) {{
					dist = (Double)arr[i];
					index = i;
				}
}			}
			return index;
		}
		public void updateIdeal(int index, int beginning) {
			if (index!=-1) {{
				{
					Token token_3_0 = new Token();
					Token token_3_1 = new Token();
					Token token_3_2 = new Token();
					// stars[beginning+index]<-getCoord()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, stars[beginning+index], "getCoord", _arguments, null, token_3_0 );
						__messages.add( message );
					}
					// setCoord(token)
					{
						Object _arguments[] = { token_3_0 };
						Message message = new Message( self, self, "setCoord", _arguments, token_3_0, token_3_1 );
						__messages.add( message );
					}
					// stars[beginning+index]<-getAvgDist()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, stars[beginning+index], "getAvgDist", _arguments, token_3_1, token_3_2 );
						__messages.add( message );
					}
					// setDist(token)
					{
						Object _arguments[] = { token_3_2 };
						Message message = new Message( self, self, "setDist", _arguments, token_3_2, null );
						__messages.add( message );
					}
				}
			}
}		}
		public void check() {
			int beginning = range*workerNum;
			int ending = beginning+range+overflow;
			{
				Token token_2_0 = new Token();
				Token token_2_1 = new Token();
				// join block
				token_2_0.setJoinDirector();
				for (int i = beginning; i<ending; i++){
					{
						// stars[i]<-getAvgDist()
						{
							Object _arguments[] = {  };
							Message message = new Message( self, stars[i], "getAvgDist", _arguments, null, token_2_0 );
							__messages.add( message );
						}
					}
				}
				addJoinToken(token_2_0);
				// findBest(token)
				{
					Object _arguments[] = { token_2_0 };
					Message message = new Message( self, self, "findBest", _arguments, token_2_0, token_2_1 );
					__messages.add( message );
				}
				// updateIdeal(token, beginning)
				{
					Object _arguments[] = { token_2_1, beginning };
					Message message = new Message( self, self, "updateIdeal", _arguments, token_2_1, currentMessage.getContinuationToken() );
					__messages.add( message );
				}
				throw new CurrentContinuationException();
			}
		}
		public Double getDist() {
			return dist;
		}
		public Double[] getCoord() {
			Double coord[] = new Double[3];
			coord[0] = x;
			coord[1] = y;
			coord[2] = z;
			return coord;
		}
	}
}