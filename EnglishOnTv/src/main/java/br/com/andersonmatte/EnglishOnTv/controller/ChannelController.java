package br.com.andersonmatte.EnglishOnTv.controller;

import br.com.andersonmatte.EnglishOnTv.Entity.Channel;
import br.com.andersonmatte.EnglishOnTv.Entity.Stream;
import br.com.andersonmatte.EnglishOnTv.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
