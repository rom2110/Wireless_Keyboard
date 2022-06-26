make:
	javac -cp /src src/*.java -d bin 
	javadoc src/*.java -d docs
	jar -cvfm Master.jar manifestMaster.mf -C bin/ . src/
	jar -cvfm Slave.jar manifestSlave.mf -C bin/ . src/

master: make
	java -jar Master.jar

slave: make
	java -jar Slave.jar

clean:
	rm *.jar
	rm -r bin docs