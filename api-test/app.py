from flask import Flask,jsonify
from flask_cors import CORS, cross_origin

app = Flask(__name__)
CORS(app)


@cross_origin
@app.route('/getTrue', methods=['GET'])
def get_true():
    return jsonify(True)


@cross_origin
@app.route('/getFalse', methods=['GET'])
def get_false():
    return jsonify(False)


app.run("localhost",port=3030,debug=True)