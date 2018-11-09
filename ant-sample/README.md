A sample use of maven-antrun-plugin

Uses foreach for manipulating every subfolder in the output directory.

Furthermore, each directory is checked for an existence of a file before further processing can occur

To execute: 

```bash
mvn clean package -pl ant-sample
```