# Example running Unit Tests (UT) and Integration Test (IT) in isolation

Check out the modules pom for the surefire(UT) plugin and the failsafe (IT) plugin.
The tests are enabled or disabled based on the maven profiles passed. 

To run UT only. 'local' is the default profile and so can be ommitted
```bash
    mvn -Plocal verify
    # or
    mvn verify
```

To run IT only.
```bash
    mvn -Pittest verify
```

To run both IT and UT
```bash
    mvn -Pfull verify
```

Additional supporting actions can be setup during pre-integration phase using the antrun plugin