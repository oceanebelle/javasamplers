package com.oceanebelle.javasamplers.cassandrasample.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CqlSupport {

    public enum Type { table, type}

    public static CqlBuilder builder(Type type, String keyspace) {
        return CqlBuilder.builder(type).keyspace(keyspace);
    }

    public static class CqlBuilder {
        private final String type;
        private String name;
        private String keyspace;
        private List<String> fields = new ArrayList<>();

        protected CqlBuilder(Type type) {
            this.type = type.name();
        }

        private static CqlBuilder builder(Type type) {
            return new CqlBuilder(type);
        }

        public CqlBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CqlBuilder keyspace(String keyspace) {
            this.keyspace = keyspace;
            return this;
        }



        public CqlBuilder addField(String... field) {
            fields.addAll(Arrays.asList(field));
            return this;
        }

        public String create() {
            Objects.requireNonNull(name);
            Objects.requireNonNull(keyspace);

            StringBuilder sb = new StringBuilder();

            //sb.append(String.format("drop %s if exists  %s.%s; \r\n", type, keyspace, name));
            sb.append(String.format("create %s %s (", type, name));
            sb.append(String.join(", ", fields));
            sb.append(");");

            return sb.toString();
        }

        public String drop() {
            Objects.requireNonNull(name);
            Objects.requireNonNull(keyspace);

            StringBuilder sb = new StringBuilder();

            sb.append(String.format("drop %s if exists %s.%s; ", type, keyspace, name));

            return sb.toString();
        }
    }
}
