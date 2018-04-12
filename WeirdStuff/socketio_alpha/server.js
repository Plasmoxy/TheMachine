const JAVA_RUN = '' // JAVA EXEC
const PORT = 5999

const express = require('express');
const http = require('http');
const socketio = require('socket.io');
const { exec } = require('child_process');

function slog(x) {
    console.log('[server.js] ' + x)
}


function serverHandler(req, res) {
    // nothing
}

const app = http.createServer(serverHandler)

slog('STARTING ...')







slog('executing JAVA_RUN')
if (JAVA_RUN && JAVA_RUN != '') exec(JAVA_RUN, (err, out, stderr) => {
    if (out) slog(' ==== OUT ====\n' + out)
    if (err) slog(' ==== ERR ====\n' + err)
    if (stderr) slog(' ==== STDERR ====\n' + stderr)
})

slog('reached script end')