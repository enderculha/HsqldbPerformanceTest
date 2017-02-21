# HsqldbPerformanceTest
This code measures hsql inmemory database by inserting and selecting to a table constantly and measuring 
time to insert, delete a row and averaging these values.
Main method takes 4 parameters which are;
insert count: how many times the prepared sql will be used to insert
insert size: size of the insert sql ((INSERT INTO iucs (value1, value2), (value 1, value 2)) -> size=2))
insert count: number of threads that make insert
timeout: how old values in db will be deleted.


