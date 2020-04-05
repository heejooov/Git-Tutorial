import urllib.request as ul
import xmltodict
import json
import sys
import io
from konlpy.tag import Okt
okt = Okt()

text = "모바일 게임은 재밌다 열심히 해서 만랩을 찍어야지~ ㅎㅎㅎ"

print(okt.morphs(text))
print(okt.morphs(text, stem=True))


sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding='utf-8')
sys.stderr = io.TextIOWrapper(sys.stderr.detach(), encoding='utf-8')
# 아톰에디터 한글사용을 위한 구문
KEY="WU3mzjeOk9dyTh0TGhzyPJgVTF896mvmogKUt4Pxmnt87Xuixv2VhA3Sfa52ZGNVSh0rhtUjiwLspxg%2Fm4hYvQ%3D%3D"
url = "http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=" + KEY +"&callTp=D&servId="+"WII00000001"
# 데이터를 받을 url

request = ul.Request(url)
# url의 데이터를 요청함

response = ul.urlopen(request)
# 요청받은 데이터를 열어줌

rescode = response.getcode()
# 제대로 데이터가 수신됐는지 확인하는 코드 성공시 200
if (rescode == 200):
    responseData = response.read()
    # 요청받은 데이터를 읽음
    rD = xmltodict.parse(responseData)
    # XML형식의 데이터를 dict형식으로 변환시켜줌

    rDJ = json.dumps(rD)
    # dict 형식의 데이터를 json형식으로 변환

    rDD = json.loads(rDJ)
    # json형식의 데이터를 dict 형식으로 변환

    print(rDD)
    # 정상적으로 데이터가 출력되는지 확인

    w_data = rDD["wantedDtl"]["alwServCn"]
    # 해당 dict형식의 파일의 item을 사용하기 편하도록 w_data에 저장

    print('돈 : ' + w_data)


# 데이터출력하기
