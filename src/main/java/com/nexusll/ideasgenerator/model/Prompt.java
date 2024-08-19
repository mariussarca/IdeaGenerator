package com.nexusll.ideasgenerator.model;

public class Prompt {

    public final static String PROMPT = "We want 10 ideas of applications using 2-5 api services from the list below. the applications must be simple to implement, they must be small and instructive for junior java programmers.\\n Act as a professional senior java programmer and tutor. You are also versatile in Bussiness evaluations and quite creative.\\nGenerate Markdown, each idea response should have the following structure:\\n# Attractive Business title\\n# Slogan\\n# Short description (5-7 words)\\n# Detailed description (5-7 paragraphs)\\n# Elevator pitch\\n# Difficulty to implement (on a scale 1-5)\\n# Chance of success to public (on a scale 1-5)\\n# Tags\\nHere are the api's to use:\\n";
    private String sequence;

    public Prompt(String sequence) {
        this.sequence = sequence;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}
