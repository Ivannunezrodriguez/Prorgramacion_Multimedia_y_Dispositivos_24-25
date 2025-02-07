using System.Runtime.CompilerServices;
using UnityEngine;
using UnityEngine.InputSystem;
public class PlayerControler : MonoBehaviour
{
    private Rigidbody rb;
    private float movementx;
    private float movementy;
public float speed = 0;
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        rb = GetComponent<Rigidbody>();
    }
void OnMove(InputValue movementValue)
{
    Vector2 movementVector = movementValue.Get<Vector2>();
    movementx = movementVector.x;
    movementy = movementVector.y;

}

   // Update is called once per frame
    void Update()
    {}
    private void FixedUpdate() {
        Vector3 movementVector = new Vector3(movementx, 0, movementy);
        rb.AddForce(movementVector*speed);
        
    }
    
}
