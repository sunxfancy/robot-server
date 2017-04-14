FROM python:3.6
ENV PYTHONUNBUFFERED 1
RUN mkdir /app
WORKDIR /app
ADD ./django_app/requirements.txt /app/
RUN pip3 install -r requirements.txt
VOLUME /app
EXPOSE 8000
CMD python3 manage.py runserver 0.0.0.0:8000
