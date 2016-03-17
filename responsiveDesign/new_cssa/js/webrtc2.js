'use strict';

/* globals webkitRTCPeerConnection */

var localStream;
var localPeerConnection;
var remotePeerConnection;

var localVideo = document.getElementById('localVideo');
var remoteVideo = document.getElementById('remoteVideo');

localVideo.addEventListener('loadedmetadata', function () {
    trace('Local video currentSrc: ' + this.currentSrc +
        ', videoWidth: ' + this.videoWidth +
        'px,  videoHeight: ' + this.videoHeight + 'px');
});

remoteVideo.addEventListener('loadedmetadata', function () {
    trace('Remote video currentSrc: ' + this.currentSrc +
        ', videoWidth: ' + this.videoWidth +
        'px,  videoHeight: ' + this.videoHeight + 'px');
});

window.onload = start;

var total = '';
var pageConsole = document.getElementById("console");

function trace(text) {
    total = text + "<br>" + total;
    pageConsole.innerHTML = total;
    console.log((window.performance.now() / 1000).toFixed(3) + ': ' + text);
}

function gotStream(stream) {
    trace('Received local stream');
    localVideo.src = URL.createObjectURL(stream);
    localStream = stream;
    call();
}

function start() {
    trace('Requesting local stream');
    navigator.getUserMedia = navigator.getUserMedia ||
        navigator.webkitGetUserMedia || navigator.mozGetUserMedia;
    navigator.getUserMedia({
            video: true
        }, gotStream,
        function (error) {
            trace('navigator.getUserMedia error: ', error);
        });
}

function call() {
    trace('Starting call');

    if (localStream.getVideoTracks().length > 0) {
        trace('Using video device: ' + localStream.getVideoTracks()[0].label);
    }
    if (localStream.getAudioTracks().length > 0) {
        trace('Using audio device: ' + localStream.getAudioTracks()[0].label);
    }

    var servers = {
        'iceServers': [
            {
                'url': 'stun:stun.l.google.com:19302'
            }
        ]
    };

    localPeerConnection = new webkitRTCPeerConnection(servers);
    trace('Created local peer connection object localPeerConnection');
    localPeerConnection.onicecandidate = gotLocalIceCandidate;

    remotePeerConnection = new webkitRTCPeerConnection(servers);
    trace('Created remote peer connection object remotePeerConnection');
    remotePeerConnection.onicecandidate = gotRemoteIceCandidate;
    localPeerConnection.onaddstream = gotRemoteStream;

    localPeerConnection.addStream(localStream);
    trace('Added localStream to localPeerConnection');
    localPeerConnection.createOffer(gotLocalDescription);
}

function gotLocalDescription(description) {
    localPeerConnection.setLocalDescription(description);
    trace('Offer from localPeerConnection: \n' + description.sdp);
    if (getDescription1() != "") {
        description = JSON.parse(getDescription1());
        trace('getDescription1: \n' + description.sdp);
        localPeerConnection.setRemoteDescription(description);
        localPeerConnection.createAnswer(gotRemoteDescription);
    }
    else {
        setDescription1(JSON.stringify(description));
        alert("new description");
        //remotePeerConnection.setRemoteDescription(description);
        //remotePeerConnection.createAnswer(gotRemoteDescription);
    }

}

function gotRemoteDescription(description) {
    trace('Answer from remotePeerConnection: \n' + description.sdp);
}

function gotRemoteStream(event) {
    trace('Received remote stream:\n' + event.stream.getVideoTracks().length);
    remoteVideo.src = URL.createObjectURL(event.stream);
    trace('Received remote stream');
}

function gotLocalIceCandidate(event) {
    if (event.candidate) {
        localPeerConnection.addIceCandidate(new RTCIceCandidate(event.candidate));
        trace('Local ICE candidate: \n' + event.candidate.candidate);
    }
}

function gotRemoteIceCandidate(event) {
    if (event.candidate) {
        localPeerConnection.addIceCandidate(new RTCIceCandidate(event.candidate));
        trace('Remote ICE candidate: \n ' + event.candidate.candidate);
    }
}

function setDescription1(description) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "../user/setDescription1?desc=" + description, true);
    xmlhttp.send();
}

function getDescription1() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "../user/getDescription1", false);
    xmlhttp.send();
    return xmlhttp.responseText;
}