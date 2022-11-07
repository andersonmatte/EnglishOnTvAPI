package br.com.andersonmatte.EnglishOnTv.repository;

import br.com.andersonmatte.EnglishOnTv.Entity.Channel;
import br.com.andersonmatte.EnglishOnTv.Entity.Stream;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ChannelRepository {

    private ArrayList<Channel> channels = new ArrayList<>();
    private ArrayList<Stream> streams = new ArrayList<>();
    private ArrayList<Channel> channelsWithStreams = new ArrayList<>();

    public List<Channel> getChannels() {
        // API FAKE busca no sonplaceholder os usuários
        final String uri = "https://iptv-org.github.io/api/channels.json";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            // Aqui é criada a lista de objeto Pessoa que vai ser parseado
            List<Channel> channelList = Arrays.asList(objectMapper.readValue(result, Channel[].class));
            this.channels = (ArrayList<Channel>) channelList.stream()
                    .filter(c -> c.getCountry().equals("AU") || c.getCountry().equals("CA") ||
                            c.getCountry().equals("IE") || c.getCountry().equals("JM") ||
                            c.getCountry().equals("NZ") || c.getCountry().equals("ZA") ||
                            c.getCountry().equals("UK") || c.getCountry().equals("US"))
                    .collect(Collectors.toList());
            if (!channelList.isEmpty()) {
//                for (Channel c : channelList) {
//                    Channel channel = new Channel(c.getId(), c.getName(), c.getCountry(), c.getLogo(), c.getStream());
//                    for (Stream stream1 : this.streams) {
//                        if (channel.getId().equals(stream1.getChannel())) {
//                            channel.setStream(stream1);
//                        }
//                        channels.add(channel);
//                    }
//                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return this.channels;
    }

    public List<Stream> getStreams() {
        // API FAKE busca no sonplaceholder os usuários
        final String uri = "https://iptv-org.github.io/api/streams.json";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            // Aqui é criada a lista de objeto Pessoa que vai ser parseado
            List<Stream> list = Arrays.asList(mapper.readValue(result, Stream[].class));
            if (!list.isEmpty()) {
                for (Stream s : list) {
                    Stream stream = new Stream(s.getChannel(), s.getUrl(), s.getStatus());
                    streams.add(stream);
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return this.streams;
    }


    private List<Channel> getChannelsBFF(String result) {
        // Lista que vai conter o retorno trabalhado para o BFF
        ArrayList<Channel> channels = new ArrayList<>();
        final String uri = "https://iptv-org.github.io/api/channels.json";
        RestTemplate restTemplate = new RestTemplate();
        String ret = restTemplate.getForObject(uri, String.class);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            List<Stream> list = Arrays.asList(mapper.readValue(ret, Stream[].class));
            if (!list.isEmpty()) {
                for (Stream s : list) {
                    Stream stream = new Stream(s.getChannel(), s.getUrl(), s.getStatus());
                    streams.add(stream);
                }
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                    // Aqui é criada a lista de objeto Pessoa que vai ser parseado
                    List<Channel> channelList = Arrays.asList(objectMapper.readValue(result, Channel[].class));
                    channels = (ArrayList<Channel>) channelList.stream()
                            .filter(c -> c.getCountry().equals("AU") || c.getCountry().equals("CA") ||
                                    c.getCountry().equals("IE") || c.getCountry().equals("JM") ||
                                    c.getCountry().equals("NZ") || c.getCountry().equals("ZA") ||
                                    c.getCountry().equals("UK") || c.getCountry().equals("US"))
                            .collect(Collectors.toList());
                    if (!channelList.isEmpty()) {
                        for (Channel c : channelList) {
                            Channel channel = new Channel(c.getId(), c.getName(), c.getCountry(), c.getLogo(), c.getStream());
                            for (Stream stream1 : this.streams) {
                                if (channel.getId().equals(stream1.getChannel())) {
                                    channel.setStream(stream1);
                                }
                                channels.add(channel);
                            }
                        }
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return channels;
    }

    public List<Channel> getChannelsWithStreams() {
        this.getChannels();
        this.getStreams();
        if (!this.channels.isEmpty() && !this.streams.isEmpty()) {
            for (Stream stream : this.streams) {
                if (stream.getChannel() != null) {
                    for (Channel channel : this.channels) {
                        if (channel.getId() != null) {
                            if (stream.getChannel().equals(channel.getId())) {
                                channel.setStream(stream);
                                this.channelsWithStreams.add(channel);
                            }
                        }
                    }
                }
            }
        }
        return this.channelsWithStreams;
    }

    public List<Channel> getChannelsCountry(String country) {
        ArrayList<Channel> list = new ArrayList<>();
        this.getChannelsWithStreams();
        if (!this.channelsWithStreams.isEmpty()) {
            list = (ArrayList<Channel>) channelsWithStreams.stream()
                    .filter(c -> c.getCountry().equals(country))
                    .collect(Collectors.toList());
        }
        return list;
    }

}