package com.shoplite;

import java.util.List;

public record Order(String id, List<Product> items, double total) {}