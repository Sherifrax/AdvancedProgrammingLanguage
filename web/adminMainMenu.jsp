<%-- 
    Document   : adminMainMenu
    Created on : Apr 30, 2022, 8:59:05 PM
    Author     : Egor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>



<div class="container" style="margin-top: 3%">

    <form action="SaveQuestion">

        <h1>Add Question</h1>

        <div class="mb-3">

            <label for="exampleFormControlTextarea1" class="form-label">Question</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="question"></textarea>

        </div>

        <div class="mb-3">

            <label for="exampleFormControlInput1" class="form-label">Answer</label>
            <input type="text" class="form-control" id="exampleFormControlInput1" name="answer">

        </div>

        <select class="form-select" aria-label="Default select example" name="course">
            <option selected value="">Select Course...</option>
            <option value="1">One</option>
            <option value="2">Two</option>
            <option value="3">Three</option>
        </select>
        
        <input type="submit" value="Add" class="btn btn-dark" style="margin-right: 10px; margin-top: 15px"/>
        <button type="submit" class="btn btn-danger" formaction="index.html" style="margin-right: 10px; margin-top: 15px">Logout</button>


    </form>

</div>