package com.epam.parser;

import com.epam.model.Component;

import java.util.List;

@FunctionalInterface
public interface Parser {
    List<Component> parse();
}
