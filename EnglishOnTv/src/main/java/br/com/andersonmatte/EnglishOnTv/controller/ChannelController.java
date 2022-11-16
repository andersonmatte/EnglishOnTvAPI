package br.com.andersonmatte.EnglishOnTv.controller;

import br.com.andersonmatte.EnglishOnTv.Entity.Channel;
import br.com.andersonmatte.EnglishOnTv.Entity.Stream;
import br.com.andersonmatte.EnglishOnTv.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://ec2-18-118-49-145.us-east-2.compute.amazonaws.com:8080", maxAge = 3600)
@RestController
@RequestMapping(path = "/api")
public class ChannelController {

    @Autowired
    protected ChannelRepository channelRepository;

    @GetMapping(path="/channels", produces = "application/json")
    public List<Channel> getChannels() {
        return channelRepository.getChannels();
    }

    @GetMapping(path="/streams", produces = "application/json")
    public List<Stream> getStreams() {
        return channelRepository.getStreams();
    }

    @GetMapping(path="/channelswithstreams", produces = "application/json")
    public List<Channel> getChannelsWithStreams() {
        return channelRepository.getChannelsWithStreams();
    }

    @GetMapping(path="/channelscountry", produces = "application/json")
    public List<Channel> getChannelsCountry(@RequestParam("country") String country) {
        return channelRepository.getChannelsCountry(country);
    }


}
