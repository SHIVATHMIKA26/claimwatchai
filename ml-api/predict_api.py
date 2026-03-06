from flask import Flask, request, jsonify
import joblib
import numpy as np

app = Flask(__name__)

model = joblib.load("claimwatch_model.pkl")

@app.route("/")
def home():
    return "ClaimWatchAI ML API Running"

@app.route("/predict", methods=["POST"])
def predict():

    data = request.json

    features = np.array([
        data["months_as_customer"],
        data["age"],
        data["policy_deductable"],
        data["policy_annual_premium"],
        data["umbrella_limit"],
        data["incident_severity"],
        data["number_of_vehicles_involved"],
        data["bodily_injuries"],
        data["total_claim_amount"]
    ]).reshape(1,-1)

    prediction = model.predict(features)[0]

    if prediction == 1:
        result = "FRAUD"
    else:
        result = "LEGIT"

    # 🔹 Print result in terminal
    print("\n===== CLAIMWATCH AI PREDICTION =====")
    print("Input Data:", data)
    print("Prediction:", result)
    print("====================================\n")

    return jsonify({
        "prediction": result
    })


if __name__ == "__main__":
    app.run(port=5000)