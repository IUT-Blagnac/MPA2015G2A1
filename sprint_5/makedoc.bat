@echo off
set ASCIIDOCDIR=.\tools\asciidoc-8.6.9\
set PLANTUMLDIR=.\tools\
set SRCDOCDIR=./srcdoc/
set BINDIR=./bin/
set SRCDOCDIRTXT=./srcdoc/fichierTXT/


@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des documentations de webOPTI
@echo ///////////////////////////////////////////////////////
rem "%JAVA_HOME%\bin\java" -jar %PLANTUMLDIR%plantuml.jar -Tpng -o %SRCDOCDIR%images %SRCDOCDIR%diag0.puml

python %ASCIIDOCDIR%asciidoc.py -a toc -a source-highlighter=pygments -o %SRCDOCDIR%docUtilisateurWebOPTI.html %SRCDOCDIRTXT%docUtilisateurWebOPTI.txt
python %ASCIIDOCDIR%asciidoc.py -a toc -a source-highlighter=pygments -o %SRCDOCDIR%docTechniqueWebOPTI.html %SRCDOCDIRTXT%docTechniqueWebOPTI.txt

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des documentations de makeoptiWeb
@echo ///////////////////////////////////////////////////////
rem "%JAVA_HOME%\bin\java" -jar %PLANTUMLDIR%plantuml.jar -Tpng -o %SRCDOCDIR%images %SRCDOCDIR%diag0.puml

python %ASCIIDOCDIR%asciidoc.py -a toc -a source-highlighter=pygments -o %SRCDOCDIR%docUtilisateurMakeOPTIweb.html %SRCDOCDIRTXT%docUtilisateurMakeOPTIweb.txt
python %ASCIIDOCDIR%asciidoc.py -a toc -a source-highlighter=pygments -o %SRCDOCDIR%docTechniqueMakeOPTIweb.html %SRCDOCDIRTXT%docTechniqueMakeOPTIweb.txt

@echo ///////////////////////////////////////////////////////
@echo // Compilation de la javadoc
@echo ///////////////////////////////////////////////////////
javadoc -classpath %BINDIR%;tools/junit.jar -d %BINDIR%/doc @files.txt


pause