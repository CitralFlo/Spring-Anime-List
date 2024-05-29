package dev.citralflo.animelist.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharacterCommand {
    private Long id;
    private String name;
    private String imageUrl;
    private VoiceActorCommand voiceActor;
}
