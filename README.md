# CEUE203 Object-Oriented Programming — Term Work

**Candidate:** Manav Bhut  
**Enrollment number:** 25IT011  
**Course:** CEUE203 Object-Oriented Programming

## What is included

This repository is an independently written practice-and-capstone implementation based on the published practical topics.  It does not include another student's reports, screenshots, Git history, IDE settings, compiled files, or personal metadata.

* `Part-A` has one runnable Java implementation per practical task.
* `MINI-BANK` is a console banking application using a small layered design, file persistence, validation, custom exceptions, and concurrent batch processing.
* `SUBMISSION_CHECKLIST.md` identifies the material that must be personally checked or supplied before submitting.

## Build and run

Use JDK 17 or later. Each Part-A task is self-contained:

```powershell
Set-Location Part-A/Practical_01/Task1_VendingMachine
javac *.java
java Driver
```

For the capstone:

```powershell
Set-Location MINI-BANK
javac -d bin src/minibank/*.java
java -cp bin minibank.MiniBank
```

No third-party libraries are required for the supplied programs.
