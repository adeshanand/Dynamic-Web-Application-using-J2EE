THIS IS A POC. ~2014. 


Requirements:
-------------
1. MS-Access 2007
2. Java[jdk-6u25-windows-i586]
3. Apache Tomcat
4. Eclipse
 
Steps:
------
1. dsn_cmsavs, dsn_lmsavs
2. java path set


Other Instructions:
------------------
copy apache-tomcat-7.0.22[rar file] to c-drive => (right click)Extract Here
copy eclipse-jee-juno-SR1-win32[rar file] to c-drive => (right click)Extract Here
install jdk-6u25-windows-i586(Recommended:uninstall any previously insatlled version of java)
set path in system variables C:\Program Files\Java\jdk1.6.0_25\bin


Start eclipse.
Assign workspace


Setting up the server
---------------------
Menu bar => window => Show view => server.(a space headed by server tab is provided)
right click in the space => new => server.
Select apache => (expand it)click on tomcat version  7.0 server. => next => Then browse the server location folder.
Next
Finish.
[if server isn`t working change its port values to 1202,1203,1204]

Dynamic web application
------------------------
right click on project explorer
new => project => click
within web choose dynamic web project.
next
type project name(like LMS)
Dynamic web module version choose 2.5
next
next
finish.

/*web content is a folder where we have to keep all the web files like
	.html
	.jsp
	images
	videos etc...
In the Project there is a folder Java Resources. It also has a sub-folder (src) where we should keep all the java files.

right click on web content => new html file => filename like col.html => next => finish*/


make a servlet
--------------
#click on project explorer => click on servlet
#(to make any servlet page we should extend it from httpservlet)
#what is servlet? Servlet is a .java file where we have in-built html code also.


