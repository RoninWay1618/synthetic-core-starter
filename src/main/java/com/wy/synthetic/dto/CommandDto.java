package com.wy.synthetic.dto;

import jakarta.validation.constraints.*;
import java.time.Instant;

public class CommandDto {

    @NotBlank
    @Size(max = 1000)
    private String description;

    @NotNull
    private CommandPriority priority;

    @NotBlank
    @Size(max = 100)
    private String author;

    @NotNull
    private Instant time;

    public @NotBlank @Size(max = 1000) String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank @Size(max = 1000) String description) {
        this.description = description;
    }

    public CommandPriority getPriority() {
        return priority;
    }

    public void setPriority(CommandPriority priority) {
        this.priority = priority;
    }

    public @NotBlank @Size(max = 100) String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank @Size(max = 100) String author) {
        this.author = author;
    }

    public @NotNull Instant getTime() {
        return time;
    }

    public void setTime(@NotNull Instant time) {
        this.time = time;
    }
}
